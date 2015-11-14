#!/bin/bash

function create_cluster() {
    ansible-playbook -i localhost k8s_single.yml
}

function install() {
    ssh $mgmt -l ubuntu "cd src/contrib/ansible && ./setup.sh"
}

function sanity_check() {
    for i in `seq 15`; do
	ssh $mgmt -l ubuntu "ansible-playbook -i src/contrib/ansible/inventory src/contrib/ansible/validate.yml"
	if [ "$?" == "0" ]; then
	    break
	fi
	sleep 60
    done
}

function run_examples() {
    ssh $mgmt -l ubuntu "ansible-playbook -i src/contrib/ansible/inventory src/contrib/ansible/examples.yml"
}

# info should display 2 slaves connected to redis

# Replication
# role:master
# connected_slaves:2
# slave0:ip=10.254.163.12,port=6379,state=online,offset=9703,lag=1
# slave1:ip=10.254.163.12,port=6379,state=online,offset=9703,lag=0

function check_guestbook() {
    for i in `seq 15`; do
	tmpfile=$(mktemp)
	ssh $mgmt -l ubuntu curl http://172.16.0.252:3000/info > $tmpfile
	if [ "$?" == "0" ]; then
	    slaves=$(echo $info | awk '/^connected_slaves:/ { split($0, array, ":"); printf "%d", array[2]; }' $tmpfile)
	    rm -f $tmpfile
	    if [ "$slaves" == "2" ]; then
		break
	    fi
	fi
	rm -f $tmpfile
	sleep 60
    done
}

create_cluster
mgmt=$(awk '/amazonaws.com/ { print $1; }' cluster.status)
install
sanity_check
run_examples
check_guestbook
