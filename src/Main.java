import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {

        FileReader reader = new FileReader("TestCode.txt");
        BufferedReader buffer = new BufferedReader(reader);
        StreamTokenizer token = new StreamTokenizer(buffer);

        Table table = new Table();

        boolean ignoreComments = true;
        token.slashStarComments(ignoreComments);
        token.slashSlashComments(ignoreComments);
        int t;
        int tkn;
        while ((t = token.nextToken()) != StreamTokenizer.TT_EOF) {

            switch (t) {

                case '\"':
                    String lit = token.sval;
                    System.out.println("Linea " + token.lineno() + ": literal: " + lit);
                    table.addToTable(lit, "literal");
                    break;

                case StreamTokenizer.TT_WORD:

                    switch (token.sval) {
                        case "void":
                        case "class":
                        case "boolean":
                        case "byte":
                        case "char":
                        case "float":
                        case "double":
                        case "int":
                        case "String":
                            System.out.println("Linea " + token.lineno() + ": declaración: " + token.sval);
                            table.addToTable(token.sval, "declaration");
                            break;

                        case "case":
                        case "default":
                        case "if":
                        case "switch":
                            System.out.println("Linea " + token.lineno() + ": estructura de control: " + token.sval);
                            table.addToTable(token.sval, "control");
                            break;

                        case "else":
                            tkn = token.nextToken();
                            switch(tkn) {
                                case StreamTokenizer.TT_WORD:
                                    if (token.sval.equals("if")){
                                        System.out.println("Linea " + token.lineno() + ": estructura de control: " + "else if");
                                        table.addToTable("else if", "control");
                                    }
                                    break;
                                default:
                                    token.pushBack();
                                    System.out.println("Linea " + token.lineno() + ": estructura de control: " + "else");
                                    table.addToTable("else", "control");
                                    break;
                            }
                            break;

                        default:
                            if (!token.sval.equals("public") && !token.sval.equals("private") && !token.sval.equals("static") && !token.sval.equals("break") && !token.sval.equals("case") && !token.sval.equals("default")) {
                                System.out.println("Linea " + token.lineno() + ": id : " + token.sval);
                                table.addToTable(token.sval, "id");
                            }
                            break;
                    }
                    break;

                case StreamTokenizer.TT_NUMBER:
                    System.out.println("Linea " + token.lineno() + ": núm : " + token.nval);
                    table.addToTable(Double.toString(token.nval), "num");
                break;

                case '+':
                    System.out.println("Linea " + token.lineno() + ": operador : " + "+");
                    table.addToTable("+", "op");
                    break;
                case '-':
                    System.out.println("Linea " + token.lineno() + ": operador : " + "-");
                    table.addToTable("-", "op");
                    break;
                case '/':
                    System.out.println("Linea " + token.lineno() + ": operador : " + "/");
                    table.addToTable("/", "op");
                    break;
                case '*':
                    System.out.println("Linea " + token.lineno() + ": operador : " + "*");
                    table.addToTable("*", "op");
                    break;
                case '^':
                    System.out.println("Linea " + token.lineno() + ": operador : " + "^");
                    table.addToTable("^", "op");
                    break;

                case '<':
                    tkn = token.nextToken();
                    switch(tkn) {
                        case '=':
                            System.out.println("Linea " + token.lineno() + ": relación : " + "<=");
                            table.addToTable("<=", "relation");
                            break;
                        default:
                            token.pushBack();
                            System.out.println("Linea " + token.lineno() + ": relación : " + "<");
                            table.addToTable("<", "relation");
                            break;
                    }
                    break;

                case '>':
                    tkn = token.nextToken();
                    switch(tkn) {
                        case '=':
                            System.out.println("Linea " + token.lineno() + ": relación : " + ">=");
                            table.addToTable(">=", "relation");
                            break;
                        default:
                            token.pushBack();
                            System.out.println("Linea " + token.lineno() + ": relación : " + ">");
                            table.addToTable(">", "relation");
                            break;
                    }
                    break;

                case '=':
                    tkn = token.nextToken();
                    switch(tkn) {
                        case '=':
                            System.out.println("Linea " + token.lineno() + ": relación : " + "==");
                            table.addToTable("==", "relation");
                            break;
                        default:
                            token.pushBack();
                            System.out.println("Linea " + token.lineno() + ": operador : " + "=");
                            table.addToTable("=", "op");
                            break;
                    }
                    break;

                default:
                    System.out.println("Linea " + token.lineno() + ": " + (char) t + " encontrado");
            }
        }

        table.printTable();
    }
}
