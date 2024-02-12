package sinerji;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Funcionario {
    String nome;
    String cargo;
    String dataContratacao;

    public Funcionario(String nome, String cargo, String dataContratacao) {
        this.nome = nome;
        this.cargo = cargo;
        this.dataContratacao = dataContratacao;
    }
}

class Secretario extends Funcionario {
    List<Double> vendas;

    public Secretario(String nome, String dataContratacao) {
        super(nome, "Secretario", dataContratacao);
        this.vendas = new ArrayList<>();
    }
}


class Vendedor extends Funcionario {
    List<Double> vendas;

    public Vendedor(String nome, String dataContratacao) {
        super(nome, "Vendedor", dataContratacao);
        this.vendas = new ArrayList<>();
    }
}

class Gerente extends Funcionario {
    public Gerente(String nome, String dataContratacao) {
        super(nome, "Gerente", dataContratacao);
    }
}

public class Empresa {

    List<Funcionario> funcionarios;
    List<Vendedor> vendedores;

    public Empresa() {
        this.funcionarios = new ArrayList<>();
        this.vendedores = new ArrayList<>();
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        if (funcionario instanceof Vendedor) {
            vendedores.add((Vendedor) funcionario);
        }
    }


    public double calcularSalarioBeneficio(List<Funcionario> listaFuncionarios, String mesAno) {
        double totalPago = 0;

        for (Funcionario funcionario : listaFuncionarios) {
            totalPago += calcularSalario(funcionario) + calcularBeneficio(funcionario, mesAno);
        }

        return totalPago;
    }

    public double calcularSalario(List<Funcionario> listaFuncionarios, String mesAno) {
        double totalSalario = 0;

        for (Funcionario funcionario : listaFuncionarios) {
            totalSalario += calcularSalario(funcionario);
        }

        return totalSalario;
    }

    public double calcularBeneficio(List<Funcionario> listaFuncionarios, String mesAno) {
        double totalBeneficio = 0;

        for (Funcionario funcionario : listaFuncionarios) {
            totalBeneficio += calcularBeneficio(funcionario, mesAno);
        }

        return totalBeneficio;
    }

    public Funcionario obterMaiorSalario(List<Funcionario> listaFuncionarios, String mesAno) {
        Funcionario funcionarioComMaiorSalario = null;
        double maiorSalario = Double.MIN_VALUE;

        for (Funcionario funcionario : listaFuncionarios) {
            double salario = calcularSalario(funcionario);
            if (salario > maiorSalario) {
                maiorSalario = salario;
                funcionarioComMaiorSalario = funcionario;
            }
        }

        return funcionarioComMaiorSalario;
    }

    public Funcionario obterMaiorBeneficio(List<Funcionario> listaFuncionarios, String mesAno) {
        Funcionario funcionarioComMaiorBeneficio = null;
        double maiorBeneficio = Double.MIN_VALUE;

        for (Funcionario funcionario : listaFuncionarios) {
            double beneficio = calcularBeneficio(funcionario, mesAno);
            if (beneficio > maiorBeneficio) {
                maiorBeneficio = beneficio;
                funcionarioComMaiorBeneficio = funcionario;
            }
        }

        return funcionarioComMaiorBeneficio;
    }

    public Vendedor obterMelhorVendedor(List<Vendedor> listaVendedores, String mesAno) {
        Vendedor melhorVendedor = null;
        double maiorVenda = Double.MIN_VALUE;

        for (Vendedor vendedor : listaVendedores) {
            double totalVendas = calcularTotalVendas(vendedor, mesAno);
            if (totalVendas > maiorVenda) {
                maiorVenda = totalVendas;
                melhorVendedor = vendedor;
            }
        }

        return melhorVendedor;
    }
    private double calcularSalario(Funcionario funcionario) {
        double salarioBase = 0;

        switch (funcionario.cargo) {
            case "Secretário":
                salarioBase = 7000;
                break;
            case "Vendedor":
                salarioBase = 12000;
                break;
            case "Gerente":
                salarioBase = 20000;
                break;
            default:
                // Tratar caso o cargo não seja reconhecido
                break;
        }

        int anosContratacao = calcularAnosContratacao(funcionario.dataContratacao);
        double beneficioPorAno = 0;

        if (anosContratacao > 0) {
            switch (funcionario.cargo) {
                case "Secretário":
                    beneficioPorAno = 1000;
                    break;
                case "Vendedor":
                    beneficioPorAno = 1800;
                    break;
                default:
                    // Gerente não tem benefício por ano
                    break;
            }
        }

        return salarioBase + beneficioPorAno * anosContratacao;
    }

    private int calcularAnosContratacao(String dataContratacao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth contratacao = YearMonth.parse(dataContratacao, formatter);
        YearMonth hoje = YearMonth.now();
        return hoje.getYear() - contratacao.getYear();
    }

    private double calcularBeneficio(Funcionario funcionario, String mesAno) {
        double beneficio = 0;

        switch (funcionario.cargo) {
            case "Secretário":
                beneficio = 0.2 * calcularSalario(funcionario);
                break;
            case "Vendedor":
                // Implementar lógica para calcular 30% sobre o valor vendido no mês
                break;
            default:
                // Gerente não tem benefício
                break;
        }

        return beneficio;
    }

    private double calcularTotalVendas(Vendedor vendedor, String mesAno) {
        double totalVendas = 0;

        switch (mesAno) {
            case "12/2021":
                totalVendas = vendedor.vendas.get(0);
                break;
            case "01/2022":
                totalVendas = vendedor.vendas.get(1);
                break;
            case "02/2022":
                totalVendas = vendedor.vendas.get(2);
                break;
            case "03/2022":
                totalVendas = vendedor.vendas.get(3);
                break;
            case "04/2022":
                totalVendas = vendedor.vendas.get(4);
                break;
            default:
                // Tratar outros meses/anos conforme necessário
                break;
        }

        return totalVendas;
    }

    public static void main(String[] args) {
        Empresa empresa = new Empresa();

        // Adicionar funcionários e vendas conforme necessário

        // Exemplo de chamadas dos métodos
        System.out.println("Total pago (salário e benefício): " + empresa.calcularSalarioBeneficio(empresa.funcionarios, "01/2022"));
        System.out.println("Total pago somente em salários: " + empresa.calcularSalario(empresa.funcionarios, "01/2022"));
        System.out.println("Total pago em benefícios: " + empresa.calcularBeneficio(empresa.funcionarios, "01/2022"));

        Funcionario maiorSalario = empresa.obterMaiorSalario(empresa.funcionarios, "01/2022");
        System.out.println("Funcionário com maior salário: " + maiorSalario.nome);

        Funcionario maiorBeneficio = empresa.obterMaiorBeneficio(empresa.funcionarios, "01/2022");
        System.out.println("Funcionário com maior benefício: " + maiorBeneficio.nome);

        Vendedor melhorVendedor = empresa.obterMelhorVendedor(empresa.vendedores, "01/2022");
        System.out.println("Melhor vendedor: " + melhorVendedor.nome);
    }
}