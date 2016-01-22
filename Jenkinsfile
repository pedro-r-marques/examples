def x = {
    node {
        try {
            sh "/bin/false"
        } catch(ex) {
            sleep 10
        }
    }
}

def fn_y() {
    retry(2) {
        try {
            sh "/bin/false"
        } catch (ex) {
            sleep 10
        }
    }

}

def y = {
    node {
        fn_y()
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
