def x = {
    node {
        try {
            sh "/bin/false"
        } catch(ex) {
            sleep 10
        }
    }
}

def y = {
    node {
        retry(2) {
            try {
                sh "/bin/false"
            } catch (ex) {
                sleep 10
            }
        }
    }
}

def tests = [
    x: x,
    y: y,
]

node {
    checkout scm
}

parallel tests
