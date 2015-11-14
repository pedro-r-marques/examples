#!/bin/bash

ansible-playbook -i localhost k8s_single.yml

mgmt=$(awk '/amazonaws.com/ { print $1; }' cluster.status)

ssh $mgmt -l ubuntu "cd src/contrib/ansible && ./setup.sh"

for i in `seq 15`; do 
    ssh $mgmt -l ubuntu "ansible-playbook -i src/contrib/ansible/inventory src/contrib/ansible/validate.yml"
    if [ "$?" == "0" ]; then
	break
    fi
    sleep 60
done

ssh $mgmt -l ubuntu "ansible-playbook -i src/contrib/ansible/inventory src/contrib/ansible/examples.yml"

# info should display 2 slaves connected to redis

# Replication
# role:master
# connected_slaves:2
# slave0:ip=10.254.163.12,port=6379,state=online,offset=9703,lag=1
# slave1:ip=10.254.163.12,port=6379,state=online,offset=9703,lag=0

for i in `seq 15`; do
    ssh $mgmt -l ubuntu curl http://172.16.0.252:3000/info
    if [ "$?" == "0" ]; then
	break
    fi
    sleep 60
done
