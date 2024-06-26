package org.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        // Sti til den lokale mappen for repository'et
        String localRepoDirPath = "/app/target-repo";

        // URL'en for det fjerne repository, som skal klones
        String remoteRepoURL = "https://github.com/tblautenberg/DockerAutoPullTarget";

        // Tjek om target mappen eksisterer, hvis ikke, så opret den
        File targetDir = new File(localRepoDirPath);
        if (!targetDir.exists()) {
            System.out.println("Target folder doesn't exist. Creating it...");
            targetDir.mkdirs(); // Opretter target mappen
        } else {
            System.out.println("Target folder already exists.");
            System.out.println("Deleting target folder since it exists with path: " + localRepoDirPath);
            FileUtils.forceDelete(targetDir);
        }

        // Klone det fjerne repository
        GitClone gitClone = new GitClone();
        gitClone.cloneRepository(localRepoDirPath, remoteRepoURL);

        System.out.println("Running Main.java in the docker container");
        System.out.println("... This process should kill itslef, and if not, then it's a bug ...");
    }
}
