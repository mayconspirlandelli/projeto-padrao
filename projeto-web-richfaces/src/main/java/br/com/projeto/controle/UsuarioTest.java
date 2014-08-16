package br.com.projeto.controle;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.projeto.entidades.Usuario;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.service.GrupoUsuarioService;
import br.com.projeto.service.UsuarioService;

public class UsuarioTest {

	private static ApplicationContext context;

	public static void main(String[] args) {
		try {
			System.out.println("************** BEGINNING PROGRAM **************");
			context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
			GrupoUsuarioService grupoUsuarioService = (GrupoUsuarioService) context.getBean("grupoUsuarioServiceImpl");
			UsuarioService usuarioService = (UsuarioService) context.getBean("usuarioServiceImpl");

			for (int i = 1; i <= 20; i++) {
				
//				usuarioService.excluir(usuarioService.consultar(i));
				
				Usuario usuario = new Usuario();
				usuario.setAtivo(true);
				usuario.setGrupoUsuario(grupoUsuarioService.consultar(1));
				usuario.setNome("Usuario_"+i);
				usuario.setLogin("User"+i);
				usuario.setSenha("senha_"+i);
				usuario.setUltimoLogin(new Date());
				
				usuarioService.incluir(usuario);
				
				System.out.println("Usuario" + usuario + " added successfully");
			}


		} /*catch (IncluirException i) {
			System.out.println(i.getMessage());
		}*/ catch (ConsultarException c) {
			System.out.println(c.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
