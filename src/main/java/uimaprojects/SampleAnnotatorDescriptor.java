/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uimaprojects;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;

/**
 *
 * @author Mohsin Uddin
 */
public class SampleAnnotatorDescriptor {
    
    public static void main(String[] args) throws Exception {
        File outputDirectory = new File("data/descriptor");
        outputDirectory.mkdirs();
        AnalysisEngineDescription aed = AnalysisEngineFactory
                .createEngineDescription(SampleAnnotator.class);
        aed.toXML(new FileOutputStream(new File(outputDirectory, "SlangAnnotator.xml")));
  }
    
}
