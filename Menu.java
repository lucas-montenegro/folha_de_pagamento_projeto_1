import java.util.Scanner;

public class Menu {
    public static void remove(String [][] employees, int position, int current_employees) {
        if(position == 0 && current_employees == 1) {
            return;
        }

        for(int i = position; i < current_employees; i++) {
            employees[i][0] = employees[i + 1][0];
            employees[i][1] = employees[i + 1][1];
            employees[i][2] = employees[i + 1][2];
            employees[i][3] = employees[i + 1][3];
            employees[i][4] = employees[i + 1][4];
        }
    }

    public static void removeEmployee(String [][] employees, int current_employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário que deseja remover ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if(option.equals("-1")) {
            for(int i = 0; i < current_employees; i++) {
                System.out.printf("Nome: %s\n", employees[i][0]);
                System.out.printf("Número do empregado: %s\n\n", employees[i][4]);
            }

            System.out.println("Digite o número do funcionário que deseja remover:");
            option = input.nextLine();
        }

        for(int i = 0; i < current_employees; i++){
            if(employees[i][4].equals(option)) {
                System.out.printf("Funcionário %s removido com sucesso!\n", employees[i][0]);
                remove(employees, i, current_employees);
                break;
            }
        }
    }

    ublic static void addEmployee(String [][] employees, int current_employees, int id) {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome do funcionário:");
        String name = input.nextLine();
        System.out.println();

        System.out.println("Digite o endereço do funcionário:");
        String address = input.nextLine();
        System.out.println();

        System.out.println("(1) Horista\n(2) Comissionado\n(3) Salariado");
        String type = input.nextLine();
        System.out.println();

        System.out.println("Digite o salário do funcionário:");
        String salary = input.nextLine();
        System.out.println();

        String id_string = Integer.toString(id);

        for(int i = 0; i < 50; i++) {
            if (i == current_employees) {
                employees[i][0] = name;
                employees[i][1] = address;
                employees[i][2] = type;
                employees[i][3] = salary;
                employees[i][4] = id_string;
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String [][] employees = new String[50][5];
        System.out.println("Bem vindo ao sistema de Folha de Pagamento!");

        int option, id = 1, current_employees = 0;

        while(true) {
            System.out.println("Digite uma das opções abaixo para executar o programa:");
            System.out.printf("0 - Parar o programa\n1 - Adicionar um funcionário\n2 - Remover um funcionário\n");
            System.out.println("Digite uma das opções:");
            option = input.nextInt();
            System.out.println();

            while(option != 0 && option != 1 && option != 2) {
                System.out.println("Digite uma opção válida:");
                option = input.nextInt();
            }

            if(option == 0) {
                break;
            }
            else if(option == 1) {
                addEmployee(employees, current_employees, id);
                current_employees++;
                id++;
            }
            else if(option == 2) {
                removeEmployee(employees, current_employees);
                current_employees--;
            }


            System.out.printf("\n-------------------------------------------------------\n\n");
        }

        System.out.println("Obrigado por utilizar o nosso sistema!");
    }
}