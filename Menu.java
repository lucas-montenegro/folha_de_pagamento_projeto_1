import java.util.Scanner;

public class Menu {
    public static void listOfEmployees(String [][] employees, int current_employees) {
        for(int i = 0; i < current_employees; i++) {
            System.out.printf("Nome: %s\n", employees[i][0]);
            System.out.printf("Id do funcionário: %s\n\n", employees[i][4]);
        }
    }

    public static void addSale(String [][] employees, int current_employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if(option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário:");
            option = input.nextLine();
        }

        for(int i = 0; i < 50; i++){
            if(employees[i][4].equals(option)) {
                System.out.printf("Digite o valor da venda que o funcionário %s, com o id %s, realizou hoje:\n", employees[i][0], employees[i][4]);
                String sale = input.nextLine();
                double sale_double = Double.parseDouble(sale);
                double actual_salary = Double.parseDouble(employees[i][7]);
                double commission = Double.parseDouble(employees[i][6]);
                double total = actual_salary + ((commission * sale_double) / 100);
                String total_string = Double.toString(total);
                employees[i][7] = total_string;

                break;
            }
        }
    }


    public static void addHoursWorked(String [][] employees, int current_employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if(option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário:");
            option = input.nextLine();
        }

        for(int i = 0; i < 50; i++){
            if(employees[i][4].equals(option)) {
                System.out.printf("Digite a hora de entrada do funcionário %s:\n", employees[i][4]);
                int entry_hour = input.nextInt();
                System.out.printf("Digite o minuto de entrada do funcionário %s:\n", employees[i][4]);
                int entry_minute = input.nextInt();
                System.out.printf("Digite a hora de saída do funcionário %s:\n", employees[i][4]);
                int exit_hour = input.nextInt();
                System.out.printf("Digite o minuto de entrada do funcionário %s:\n", employees[i][4]);
                int exit_minute = input.nextInt();

                int total_hours;

                if(exit_hour == entry_hour) {
                    total_hours = 24;
                }
                else if(entry_hour > exit_hour) {
                    total_hours = 24 - entry_hour + exit_hour;
                }
                else {
                    total_hours = exit_hour - entry_hour;
                }

                if(entry_minute > exit_minute) {
                    total_hours--;
                }

                if(employees[i][2].equals("1")) {
                    double actual_salary = Double.parseDouble(employees[i][7]);
                    double salary = Double.parseDouble(employees[i][3]);

                    if(total_hours > 8) {
                        actual_salary += salary * 8 + (salary * 1.5 * (total_hours - 8));
                    }
                    else {
                        actual_salary += salary * total_hours;
                    }

                    employees[i][7] = Double.toString(actual_salary);
                }

                break;
            }
        }
    }

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
            employees[i][5] = employees[i + 1][5];
        }
    }

    public static void removeEmployee(String [][] employees, int current_employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário que deseja remover ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if(option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário que deseja remover:");
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

    public static void addEmployee(String [][] employees, int current_employees, int id) {
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

        System.out.println("Digite a comissão, se existir, do funcionário:");
        String commission = input.nextLine();
        System.out.println();

        String id_string = Integer.toString(id);

        for(int i = 0; i < 50; i++) {
            if (i == current_employees) {
                employees[i][0] = name;
                employees[i][1] = address;
                employees[i][2] = type;
                employees[i][3] = salary;
                employees[i][4] = id_string;
                employees[i][5] = "0";
                employees[i][6] = commission;
                employees[i][7] = "0";

                if(employees[i][2].equals("3")) {
                    employees[i][7] = employees[i][3];
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String [][] employees = new String[50][8];
        System.out.println("Bem vindo ao sistema de Folha de Pagamento!");

        int option, id = 1, current_employees = 0;

        while(true) {
            System.out.println("Digite uma das opções abaixo para executar o programa:");
            System.out.printf("\n0 - Parar o programa\n1 - Adicionar um funcionário\n2 - Remover um funcionário\n3 - Lançar um cartão de ponto\n4 - Adicionar venda\n\n");
            System.out.println("Digite uma das opções:");
            option = input.nextInt();
            System.out.println();

            while(option != 0 && option != 1 && option != 2 && option != 3 && option != 4) {
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
            else if(option == 3) {
                addHoursWorked(employees, current_employees);
            }
            else if(option == 4) {
                addSale(employees, current_employees);
            }


            System.out.printf("\n-------------------------------------------------------\n\n");
        }

        for(int i = 0; i < current_employees; i++) {
            System.out.printf("Nome: %s\n", employees[i][0]);
            System.out.printf("Tipo: %s\n", employees[i][2]);
            System.out.printf("Salário Atual: %s\n\n", employees[i][7]);
        }

        System.out.println("Obrigado por utilizar o nosso sistema!");
    }
}

/* 0 -> nome
   1 -> endereço
   2 -> tipo
   3 -> salário
   4 -> id
   5 -> horas trabalhadas no dia // ajeitar
   6 -> porcentagem da comissao
   7 -> salário atual
 */
