#!/bin/sh
# description: Auto-starts boot
# $API_HOME/bin/boot.sh
#启动命令： boot.sh start
#重启命令： boot.sh restart
#关闭命令： boot.sh stop
#是否运行： boot.sh status

CURRENT_DIR=`dirname $0`
API_HOME=`cd "$CURRENT_DIR/.." >/dev/null; pwd`
Tag="mall"
cd $API_HOME
Log="$API_HOME/log/test.log"
Lib="$API_HOME/lib/"
Jar="$API_HOME/mall-0.0.1-SNAPSHOT.jar"
RETVAL="0"

# See how we were called.
function start()
{
    echo $$ > $API_HOME/api.pid
    nohup $JAVA_HOME/jre/bin/java -Xms512m -Xmx512m \
    -XX:-UseGCOverheadLimit \
    -verbose:gc -Xloggc:jvm-gc.log \
    -Dappliction=$Tag \
#    -Dloader.path=$Lib \
    -jar $Jar --spring.config.location=$API_HOME/config > $API_HOME/log/api_stdout.log 2>&1 &
    # tail -f -n 0 $API_HOME/log/api_stdout.log
    echo "$Tag started!"
}


function stop()
{
    pid=$(ps -ef | grep -v 'grep' | egrep $Tag| awk '{printf $2 " "}')
    if [ "$pid" != "" ]; then
        echo -n "boot ( pid: $pid) is running"
        echo
        echo -n "Shutting down boot..."
        echo
        kill -9 "$pid" > /dev/null 2>&1
    fi
        status

}

function status()
{
    pid=$(ps -ef | grep -v 'grep' | egrep $Tag| awk '{printf $2 " "}')
    #echo "$pid"
    if [ "$pid" != "" ]; then
        echo "boot is running,pid is $pid"
    else
        echo "boot is stopped"
    fi
}



function usage()
{
   echo "Usage: $0 {start|stop|restart|status}"
   RETVAL="2"
}

# See how we were called.
RETVAL="0"
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        stop
        start
        ;;
    reload)
        RETVAL="3"
        ;;
    status)
        status
        ;;
    *)
      usage
      ;;
esac
exit $RETVAL