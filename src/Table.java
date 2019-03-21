import java.util.ArrayList;

public class Table {
    private ArrayList<String> declarations;
    private ArrayList<String> id;
    private ArrayList<String> literal;
    private ArrayList<String> num;
    private ArrayList<String> op;
    private ArrayList<String> control;
    private ArrayList<String> relation;

    public Table(){
        this.declarations = new ArrayList<>();
        this.id = new ArrayList<>();
        this.literal = new ArrayList<>();
        this.num = new ArrayList<>();
        this.op = new ArrayList<>();
        this.control = new ArrayList<>();
        this.relation = new ArrayList<>();
    }

    public void addToTable(String token, String type){
        switch (type){
            case "declaration":
                this.declarations.add(token);
                break;

            case "id":
                this.id.add(token);
                break;

            case "literals":
                this.literal.add(token);
                break;

            case "num":
                this.num.add(token);
                break;

            case "op":
                this.op.add(token);
                break;

            case "control":
                this.control.add(token);
                break;

            case "relation":
                this.relation.add(token);
                break;
        }
    }

    public void printTable(){
        System.out.println("declaraciones:" + this.declarations.toString());
        System.out.println("id:" + this.id.toString());
        System.out.println("literal:" + this.literal.toString());
        System.out.println("num:" + this.num.toString());
        System.out.println("operadores:" + this.op.toString());
        System.out.println("estructuras de control:" + this.control.toString());
        System.out.println("relación:" + this.relation.toString());

    }

}
