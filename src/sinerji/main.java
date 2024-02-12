package sinerji;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {

	public static void main(String[] args) {
		  Empresa empresa = new Empresa();

		    // Adicione alguns funcionários e vendedores
		    empresa.adicionarFuncionario(new Secretario("Jorge Carvalho", "01/2018"));
		    empresa.adicionarFuncionario(new Secretario("Maria Souza", "12/2015"));
		    empresa.adicionarFuncionario(new Vendedor("Ana Silva", "12/2021"));
		    empresa.adicionarFuncionario(new Vendedor("João Mendes", "12/2021"));
		    empresa.adicionarFuncionario(new Gerente("Juliana Alves", "07/2017"));
		    empresa.adicionarFuncionario(new Gerente("Bento Albino", "03/2014"));

		    // Adicione algumas vendas para os vendedores
		    empresa.vendedores.get(0).vendas = Arrays.asList(5200.0, 4000.0, 4200.0, 5850.0, 7000.0);
		    empresa.vendedores.get(1).vendas = Arrays.asList(3400.0, 7700.0, 5000.0, 5900.0, 6500.0);

		    // Exemplo de chamadas dos métodos
		    System.out.println("Total pago (salário e benefício): " + empresa.calcularSalarioBeneficio(empresa.funcionarios, "01/2022"));
		    System.out.println("Total pago somente em salários: " + empresa.calcularSalario(empresa.funcionarios, "01/2022"));
		    System.out.println("Total pago em benefícios: " + empresa.calcularBeneficio(empresa.funcionarios, "01/2022"));

		    Funcionario maiorSalario = empresa.obterMaiorSalario(empresa.funcionarios, "01/2022");
		    if (maiorSalario != null) {
		        System.out.println("Funcionário com maior salário: " + maiorSalario.nome);
		    } else {
		        System.out.println("Nenhum funcionário encontrado.");
		    }

		    Funcionario maiorBeneficio = empresa.obterMaiorBeneficio(empresa.funcionarios, "01/2022");
		    if (maiorBeneficio != null) {
		        System.out.println("Funcionário com maior benefício: " + maiorBeneficio.nome);
		    } else {
		        System.out.println("Nenhum funcionário encontrado.");
		    }

		    Vendedor melhorVendedor = empresa.obterMelhorVendedor(empresa.vendedores, "01/2022");
		    if (melhorVendedor != null) {
		        System.out.println("Melhor vendedor: " + melhorVendedor.nome);
		    } else {
		        System.out.println("Nenhum vendedor encontrado.");
		    }
	}
}
