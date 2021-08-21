#!/usr/bin/env bash
# Use this script to test if a given TCP host/port are available

WF_cmdname=${0##*/}  # cmd name

echoerr()
{
  if [[ $WF_QUIET -ne 1 ]];then
    echo "$@" 1>&2; # $@ = process arguments list
   fi
}

usage()
{
    cat << USAGE >&2
Usage:
    $WF_cmdname host:port [-s] [-t timeout] [-- command args]
    -h HOST | --host=HOST       Host or IP under test
    -p PORT | --port=PORT       TCP port under test
                                Alternatively, you specify the host and port as host:port
    -s | --strict               Only execute subcommand if the test succeeds
    -q | --quiet                Don't output any status messages
    -t TIMEOUT | --timeout=TIMEOUT
                                Timeout in seconds, zero for no timeout
    -- COMMAND ARGS             Execute command with args after the test finishes
USAGE
    exit 1
}

wait_for()
{
    if [[ $WF_TIMEOUT -gt 0 ]]; then
        echoerr "$WF_cmdname: waiting $WF_TIMEOUT seconds for $WF_HOST:$WF_PORT"
    else
        echoerr "$WF_cmdname: waiting for $WF_HOST:$WF_PORT without a timeout"
    fi

    WF_start_ts=$(date +%s)

    while :
    do
        if [[ $WF_ISBUSY -eq 1 ]]; then
            nc -z $WF_HOST $WF_PORT   # scan host:port
            WF_result=$? # $? = command return value
        else
            (echo -n > /dev/tcp/$WF_HOST/$WF_PORT) >/dev/null 2>&1
            WF_result=$?
        fi

        if [[ $WF_result -eq 0 ]]; then
            WF_end_ts=$(date +%s)
            echoerr "$WF_cmdname: $WF_HOST:$WF_PORT is available after $((WF_end_ts - WF_start_ts)) seconds"
            break
        fi

        sleep 1
    done

    return $WF_result
}

wait_for_wrapper()
{
    # In order to support SIGINT during timeout: http://unix.stackexchange.com/a/57692
    if [[ $WF_QUIET -eq 1 ]]; then
        timeout $WF_BUSYFLAG $WF_TIMEOUT $0 --quiet --child --host=$WF_HOST --port=$WF_PORT --timeout=$WF_TIMEOUT &
    else
        timeout $WF_BUSYFLAG $WF_TIMEOUT $0 --child --host=$WF_HOST --port=$WF_PORT --timeout=$WF_TIMEOUT &
    fi

    WF_PID=$!  # $! = process PID
    trap "kill -INT -$WF_PID" INT  # INT = ctrl + C
    wait $WF_PID
    WF_RESULT=$?
    if [[ $WF_RESULT -ne 0 ]]; then
        echoerr "$WF_cmdname: timeout occurred after waiting $WF_TIMEOUT seconds for $WF_HOST:$WF_PORT"
    fi
    return $WF_RESULT
}

# process arguments
while [[ $# -gt 0 ]]  # $# = the number of process arguments
do
    case "$1" in

        *:* )
        WF_hostport=(${1//:/ })
        WF_HOST=${WF_hostport[0]}
        WF_PORT=${WF_hostport[1]}
        shift 1
        ;;

        --child)
        WF_CHILD=1
        shift 1
        ;;

        -q | --quiet)
        WF_QUIET=1
        shift 1
        ;;

        -s | --strict)
        WF_STRICT=1
        shift 1
        ;;

        -h)
        WF_HOST="$2"
        if [[ $WF_HOST == "" ]]; then break; fi
        shift 2
        ;;

        --host=*)
        WF_HOST="${1#*=}"
        shift 1
        ;;

        -p)
        WF_PORT="$2"
        if [[ $WF_PORT == "" ]]; then break; fi
        shift 2
        ;;

        --port=*)
        WF_PORT="${1#*=}"
        shift 1
        ;;

        -t)
        WF_TIMEOUT="$2"
        if [[ $WF_TIMEOUT == "" ]]; then break; fi
        shift 2
        ;;

        --timeout=*)
        WF_TIMEOUT="${1#*=}"
        shift 1
        ;;

        --)
        shift
        WF_CLI=("$@")
        break
        ;;

        --help)
        usage
        ;;

        *)
        echoerr "Unknown argument: $1"
        usage
        ;;
    esac
done


if [[ "$WF_HOST" == "" || "$WF_PORT" == "" ]]; then
    echoerr "Error: you need to provide a host and port to test."
    usage
fi


WF_TIMEOUT=${WF_TIMEOUT:-15}  # if WF_TIMEOUT exist and not empty, WF_TIMEOUT = WF_TIMEOUT,else WF_TIMEOUT = 15
WF_STRICT=${WF_STRICT:-0}
WF_CHILD=${WF_CHILD:-0}
WF_QUIET=${WF_QUIET:-0}


# Check to see if timeout is from busybox?
WF_TIMEOUT_PATH=$(type -p timeout)  # print the command path of the timeout
WF_TIMEOUT_PATH=$(realpath $WF_TIMEOUT_PATH 2>/dev/null || readlink -f $WF_TIMEOUT_PATH)
#  readlink -f = realpath


WF_BUSYFLAG=""
if [[ $WF_TIMEOUT_PATH =~ "busybox" ]]; then
    WF_ISBUSY=1
    # Check if busybox timeout uses -t flag
    # (recent Alpine versions don't support -t anymore)
    if timeout &>/dev/stdout | grep -q -e '-t '; then
        WF_BUSYFLAG="-t"
    fi
else
    WF_ISBUSY=0
fi


if [[ $WF_CHILD -gt 0 ]]; then
    wait_for
    WF_RESULT=$?
    exit $WF_RESULT
else
    if [[ $WF_TIMEOUT -gt 0 ]]; then
        wait_for_wrapper
        WF_RESULT=$?
    else
        wait_for
        WF_RESULT=$?
    fi
fi


if [[ $WF_CLI != "" ]]; then
    if [[ $WF_RESULT -ne 0 && $WF_STRICT -eq 1 ]]; then
        echoerr "$WF_cmdname: strict mode, refusing to execute subprocess"
        exit $WF_RESULT
    fi
    exec "${WF_CLI[@]}"
else
    exit $WF_RESULT
fi
