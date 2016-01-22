def fn_x() {
    // for (int i = 0; i < 2; i++) {
    //     if (0 < i) {
            try {
                sh "/bin/false"
            } catch(ex) {
                sleep 10
            }
    //     }
    // }
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
