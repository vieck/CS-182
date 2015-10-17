import java.util.ArrayList;
import java.util.List;

/**
 * Created by mvieck on 10/14/2015.
 */
public class VariableBinaryTree {

    Node rootNode;
    int size;
    List<ArrayList<Boolean>> booleanArray;

    public VariableBinaryTree(int variableCnt) {
        rootNode = new Node();
        size = variableCnt;
        booleanArray = new ArrayList<ArrayList<Boolean>>();
        fillTreeToDepth(rootNode, 1, variableCnt);
    }

    public void fillTreeToDepth(Node node, int currentDepth, int depthToAdd) {
        if (currentDepth == depthToAdd+1) {
            return;
        } else {
            node.setLeftNode(new Node(node, true, currentDepth));
            node.setRightNode(new Node(node, false, currentDepth));
            fillTreeToDepth(node.getLeftNode(), currentDepth+1, depthToAdd);
            fillTreeToDepth(node.getRightNode(), currentDepth+1, depthToAdd);
            return;
        }
    }

    public List<ArrayList<Boolean>> getBooleanArray() {
        return booleanArray;
    }

    public void recurseTreeToLeaf(VariableBinaryTree.Node node, ArrayList<Boolean> array, int depth) {
        if (node == null) {
            return;
        }
        System.out.println();
        array.add(node.value);
        depth += 1;
        if (node.getLeftNode() == null && node.getRightNode() == null) {
            booleanArray.add(array);
        } else {
            recurseTreeToLeaf(node.getLeftNode(), array, depth);
            recurseTreeToLeaf(node.getRightNode(), array, depth);
        }
    }


    public void printNodes(Node node) {
        if (node == null) {
            return;
        } else if (node.isEmpty()) {
            System.out.println(""+node.depth + " :" + node.value);
        } else {
            System.out.println(""+node.depth + " :" + node.value);
            printNodes(node.getLeftNode());
            printNodes(node.getRightNode());
        }
    }


    public class Node {
        int depth;
        boolean value;
        private Node leftNode;
        private Node rightNode;
        private Node parentNode;

        public Node() {
            this.parentNode = null;
            this.leftNode = null;
            this.rightNode = null;
        }

        public Node(Node parentNode, boolean value, int depth) {
            this.parentNode = parentNode;
            this.leftNode = null;
            this.rightNode = null;
            this.value = value;
            this.depth = depth;
        }

        public boolean isEmpty() {
            if (leftNode == null && rightNode == null) {
                return true;
            }
            return false;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public boolean isValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public Node getParentNode() {
            return parentNode;
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }
    }

}
