process_id=`cat nohupPid.txt`;
kill -9 $process_id;
rm nohup.out 2> /dev/null;
rm mpcu_application.log* 2> /dev/null;