package com.prova;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.prova.service.DocumentService;

public class DirectoryWatcher {    
	
		public static void main(String[] args) {
			  WatchService watchService;
			try {
				watchService = FileSystems.getDefault().newWatchService();
				Path path = Paths.get("C:\\data\\in");
				path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
				WatchKey key;
				while ((key = watchService.take()) != null) {
					for (WatchEvent<?> event : key.pollEvents()) {
						
						DocumentService service = new DocumentService(event.context().toString());
						service.readFile();
						service.writeFile();
						
						System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
					}
					key.reset();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
