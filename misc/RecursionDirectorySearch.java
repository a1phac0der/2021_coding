package misc;

import java.io.File;

public class RecursionDirectorySearch {
	public static void printFilesInDirectory(String directoryPath) {
		File directory = new File(directoryPath);
		if (directory.exists()) {
			if (directory.isFile())
				System.out.println(directory.getName());
			else if (directory.isDirectory()) {
				File[] directories = directory.listFiles();
				for (File file : directories) {
					printFilesInDirectory(file.getAbsolutePath());
				}
			}
		}
	}
	
	public static Long diskUsage(String directoryPath, Long size) {
		File directory = new File(directoryPath);
		if (directory.exists()) {
			if (directory.isFile())
				return size+=directory.length();
			else if (directory.isDirectory()) {
				File[] directories = directory.listFiles();
				for (File file : directories) {
					size+=diskUsage(file.getAbsolutePath(), size);
				}
			}
		}
		return size;
	}
	
	public static Long getTotalDiskUsage(String directoryPath){
		Long sum = new Long(0);
		return diskUsage(directoryPath, sum);
	}
	
	public static void main(String[] args) {
		printFilesInDirectory("/Users/sridharvinodarao/Downloads/uTorrent/downloaded/Batman Begins (2005)/");
		System.out.println("Total disk space occupied by all thse files: " + new Double(getTotalDiskUsage("/Users/sridharvinodarao/Downloads/uTorrent/downloaded/Batman Begins (2005)/")*(0.000001)).intValue());
	}
}
