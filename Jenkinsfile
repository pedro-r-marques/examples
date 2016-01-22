def x() {
    node {
    	 sleep 10
    }
}

def y() {
    node {
    	 sleep 10
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