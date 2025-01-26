package controller.command;

import controller.command.FrontCommands.CadastroCommand;
import controller.command.FrontCommands.FormCadastroCommand;
import controller.command.FrontCommands.FormLoginCommand;
import controller.command.FrontCommands.LoginCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontCommandFactory {
	
	public static Command getCommand(String action, HttpServletRequest request, HttpServletResponse response) {
		if(action == null || action.isEmpty()) {
			return new ErrorCommand();
		}else {
			
			if("loginForm".equals(action)) { 
				return new FormLoginCommand();
			} else if("getLogin".equals(action)) {
				return new LoginCommand();
			} else if("cadastroForm".equals(action)) {
				return new FormCadastroCommand();
			} else if("cadastro".equals(action)) {
				return new CadastroCommand();
			} else if("getLogin".equals(action)) {
				return new LoginCommand();
			} else if("redirecionarLink".equals(action)) { 
				return new RedirecionarLinkCommand();
			} else if("errorPage".equals(action)) { 
				return new ErrorPageCommand();
			}else {
				return new ErrorCommand();
			}
		}
	}
}
