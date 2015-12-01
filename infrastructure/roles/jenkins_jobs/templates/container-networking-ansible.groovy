def tests

node {
    git url: 'https://github.com/Juniper/container-networking-ansible.git'
    def script = load 'test/jenkins.groovy'
    tests = script.getTestMatrix()
}

parallel tests
