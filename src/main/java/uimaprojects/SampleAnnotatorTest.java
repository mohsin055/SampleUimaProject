/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uimaprojects;

import uimaprojects.token.type.Slang;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import static org.apache.uima.fit.util.JCasUtil.select;
import org.apache.uima.jcas.JCas;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.util.JCasUtil.select;

/**
 *
 * @author Mohsin Uddin
 */
public class SampleAnnotatorTest {
    
    public static void main(String[] args) throws UIMAException, Exception {
    String text = "";
    AnalysisEngine analysisEngine = createEngine(SampleAnnotator.class);
    JCas jCas = analysisEngine.newJCas();
    double startMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024d / 1024d;
    long startTime = System.currentTimeMillis();
      
    for(int i=0;i<1000;i++) {
        jCas.setDocumentText(text);
        analysisEngine.process(jCas);
        analysisEngine.collectionProcessComplete();
        for (Slang slang : select(jCas, Slang.class)) {
          //System.out.println(slang.getCoveredText() + "\tisSlang = " + slang.getIsSlang()+"\t probability = "+slang.getProbability());
        }
        jCas.reset();
    }
    long elapsedTime = System.currentTimeMillis();
    double endMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024d / 1024d;
    System.out.println(" memory to classify :" + (endMemory - startMemory));
    System.out.println(" time to classify :" + (elapsedTime - startTime));
    //testAnnotator1(text);
    testXmiWriter(text);
  }
    
  public static void testAnnotator1(String text) throws Exception {
    AnalysisEngine slangAnnotatorAE = AnalysisEngineFactory
            .createEngine(SampleAnnotator.class);
    JCas jCas = slangAnnotatorAE.newJCas();
    jCas.setDocumentText(text);
    slangAnnotatorAE.process(jCas);
    Slang slang = JCasUtil.selectByIndex(jCas, Slang.class, 4);
    System.out.println(slang.getCoveredText() + "\tisSlang = " + slang.getIsSlang()+"\t probability = "+slang.getProbability());

   
  }
  
  public static void testXmiWriter(String text) throws Exception {
    /*AnalysisEngine slangAnnotatorAE = AnalysisEngineFactory.createEngine(SampleAnnotator.class);
    JCas jCas = slangAnnotatorAE.newJCas();
    jCas.setDocumentText(text);
    slangAnnotatorAE.process(jCas);
    Slang slang = JCasUtil.selectByIndex(jCas, Slang.class, 4);
    System.out.println(slang.getCoveredText() + "\tisSlang = " + slang.getIsSlang()+"\t probability = "+slang.getProbability());
    */
    AnalysisEngine a1 = AnalysisEngineFactory.createEngine(SampleAnnotator.class);
    AnalysisEngine xWriter = AnalysisEngineFactory.createEngine(XmiWriter.class,
            XmiWriter.PARAM_OUTPUT_DIRECTORY,
            "data/xmi");
    JCas jCas = JCasFactory.createJCas();
    jCas.setDocumentText(text);
    a1.process(jCas);
    xWriter.process(jCas);
    xWriter.collectionProcessComplete();
   
  }
    
}
