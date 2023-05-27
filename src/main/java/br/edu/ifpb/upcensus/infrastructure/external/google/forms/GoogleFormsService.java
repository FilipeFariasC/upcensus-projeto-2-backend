package br.edu.ifpb.upcensus.infrastructure.external.google.forms;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;
import com.google.api.services.drive.model.PermissionList;
import com.google.api.services.forms.v1.Forms;
import com.google.api.services.forms.v1.model.Form;
import com.google.api.services.forms.v1.model.Info;

import br.edu.ifpb.upcensus.infrastructure.external.google.enums.GooglePermissionRoles;

@Service
public class GoogleFormsService {
	
	@Autowired
	private static Forms forms;

	public static Form createForm(Forms formsService, String token, String title,String description) throws IOException {
		Form form = new Form();

		form.setInfo(new Info());
		form.getInfo().setTitle(title);
		form.getInfo().setDocumentTitle(title);
		form.getInfo().setDescription(description);

		form = formsService.forms().create(form)
				.setAccessToken(token)
				.execute();

		return form;

	}
	
	/*public static List<Form> getAll(){
		List<Form> response = forms.forms().list().execute();
        return response.getForms();
	}*/

	public static void publishForm(Drive driveService,Form form, String token, GooglePermissionRoles role, List<Permission> permissions) throws IOException {
		String formId = form.getFormId();

		for (Permission permission : permissions) {
			driveService.permissions().create(formId,permission).setOauthToken(token).execute();
		}
		


	}
	






}
