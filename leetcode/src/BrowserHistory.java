
class BrowserNode {
    public String value;
    public BrowserNode next;
    public BrowserNode prev;

    public BrowserNode(String value){
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}


class BrowserHistory {

    private BrowserNode current;
    public BrowserHistory(String homepage) {
        current = new BrowserNode(homepage);
    }

    public void visit(String url) {
        BrowserNode node = new BrowserNode(url);
        current.next = node;
        node.prev = current;
        current = node;
    }

    public String back(int steps) {
        while(current.prev!=null && steps > 0){
            current = current.prev;
            steps--;
        }
        return current.value;
    }

    public String forward(int steps) {
        while(current.next!=null && steps > 0){
            current = current.next;
            steps--;
        }
        return current.value;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */