def fn_x() {
    try {
        sh "/bin/false"
    } catch(ex) {
        // Fails:
        // sleep 10
        // sleep 10i
        steps.sleep(10)
    }
}

def x = {
    node {
        fn_x()
    }
}

def fn_y() {
    retry(2) {
        try {
            sh "/bin/false"
        } catch (ex) {
            echo "${ex}"
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
