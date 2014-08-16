package br.com.projeto.controle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.projeto.entidades.GrupoUsuario;
import br.com.projeto.exception.IncluirException;
import br.com.projeto.service.GrupoUsuarioService;

public class GrupoUsuarioTest {

	private static ApplicationContext context;

	public static void main(String[] args) {
		try {
			System.out.println("************** BEGINNING PROGRAM **************");
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
			GrupoUsuarioService grupoUsuarioService = (GrupoUsuarioService) context.getBean("grupoUsuarioServiceImpl");
			
			GrupoUsuario grupoUsuario = new GrupoUsuario();
			grupoUsuario.setAtivo(true);
			grupoUsuario.setDescricao("Grupo 3");
			
			grupoUsuarioService.incluir(grupoUsuario);
			
			System.out.println("Grupo : " + grupoUsuario + " added successfully");

		} catch (IncluirException i) {
			System.out.println(i.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
