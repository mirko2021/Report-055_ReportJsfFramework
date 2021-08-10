package bean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

/**
 * Листање свих УРЛ ХТМЛ страница и боокмаркинг. 
 * @author MV
 * @version 1.1
 */
public class FileListingBean implements Serializable{
	private static final long serialVersionUID = 6290811586730289496L;
	public String PATH = "E:\\Enviroment\\Y28-Java\\Workspace-1\\055_ReportJsfFramework\\src\\main\\webapp";
	private File randomFileSelect = null; 
	
	public FileListingBean() {
		List<File> files = files();
		Random random = new Random();
		int index = Math.abs(random.nextInt()%files.size()); 
		randomFileSelect =  files.get(index); 
	}
	
	public List<File> files(){
		ArrayList<File> destionation = new ArrayList<>();
		destionation.addAll(recursive(new File(PATH)));
		Collections.sort(destionation);
		return destionation; 
	}
	
	public List<File> recursive(File dir){
		ArrayList<File> results = new ArrayList<>();
		if(dir==null) return results; 
		for(File file: dir.listFiles()) {
			if(file.isFile()) results.add(file);
			else if(file.isDirectory()) results.addAll(recursive(file));
		}
		return results;
	}
		
	public String relative(File file) {
		if(file==null) return "";
		if(!file.getAbsolutePath().startsWith(PATH)) return "";
		return file.getAbsolutePath().substring(PATH.length()); 
	}
	
	public String web(HttpServletRequest request, File file) {
		return request.getContextPath()+"/"+relative(file);
	}

	public File getRandomFileSelect() {
		return randomFileSelect;
	}
	
	public void randomFileSelect() {
		List<File> files = files();
		Random random = new Random();
		int index = Math.abs(random.nextInt()%files.size()); 
		randomFileSelect =  files.get(index); 
	}
}
