This is a vagrant configuration used to test ansible provisioning.

`vagrant up` generates an inventory file in
`cwd`.vagrant/provisioners/ansible/inventory/vagrant_ansible_inventory.
This can then be inserted into the ansible inventory of the play being
tested.

example:

```
[opencontrail:children]
nodes

[opencontrail:vars]
http_proxy=http://192.168.99.100:3128
https_proxy=http://192.168.99.100:3128
ansible_ssh_user=vagrant

[nodes]
<... vagrant_ansible_inventory ...>
```

In the example above docker-machine + docker where used to start
an http-proxy (squid) to test the http_proxy setting on this play.

```
docker run --name squid -d --publish 3128:3128 sameersbn/squid
```
