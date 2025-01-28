package controller.command;

import controller.command.FrontCommands.CadastroCommand;
import controller.command.FrontCommands.FormCadastroCommand;
import controller.command.FrontCommands.FormLoginCommand;
import controller.command.FrontCommands.LoginCommand;
import controller.command.LoggedCommands.ListarAcessosCommand;
import controller.command.LoggedCommands.DeleteCommand;
import controller.command.LoggedCommands.EncurtarLinkCommand;
import controller.command.LoggedCommands.ListarLinksCommand;
import controller.command.LoggedCommands.LogoutCommand;
import controller.command.LoggedCommands.PageAcessosCommand;
import controller.command.LoggedCommands.PageEncurtadorCommand;
import controller.command.LoggedCommands.PageMeusLinksCommand;
import controller.command.LoggedCommands.PagePersonalizarCommand;
import controller.command.LoggedCommands.PageUpdateCommand;
import controller.command.LoggedCommands.PersonalizarLinkCommand;
import controller.command.LoggedCommands.UpdateLinkCommand;
import controller.command.LoggedCommands.UserPageCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoggedCommandFactory {
	
	public static Command getCommand(String action, HttpServletRequest request, HttpServletResponse response) {
		if(action == null || action.isEmpty()) {
			return new ErrorCommand();
		}else {
			
			if("userPage".equals(action)) { 
				return new UserPageCommand();
			} else if("logout".equals(action)) { 
				return new LogoutCommand();		
			} else if("pageEncurtador".equals(action)) { 
				return new PageEncurtadorCommand();	
			} else if("pagePersonalizarLink".equals(action)) { 
				return new PagePersonalizarCommand();
			} else if("personalizarLink".equals(action)) { 
				return new PersonalizarLinkCommand();
			} else if("encurtarLink".equals(action)) { 
				return new EncurtarLinkCommand();
			} else if("listLinks".equals(action)) { 
				return new ListarLinksCommand();
			} else if("pageMeusLinks".equals(action)) { 
				return new PageMeusLinksCommand();
			} else if("delete".equals(action)) { 
				return new DeleteCommand();
			} else if("pageUpdate".equals(action)) { 
				return new PageUpdateCommand();
			} else if("updateLink".equals(action)) { 
				return new UpdateLinkCommand();
			}else if("verDados".equals(action)) {
				return new ListarAcessosCommand();
			}else if("pageAcessos".equals(action)){
				return new PageAcessosCommand();
			}	
			else {
			
				return new ErrorCommand();
			}
		}
	}
	
}
