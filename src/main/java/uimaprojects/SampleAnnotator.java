/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uimaprojects;


import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.TypeCapability;
import uimaprojects.token.type.Slang;
import java.util.List;
import org.apache.uima.UimaContext;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;

@TypeCapability(outputs = { 
    "uimaprojects.token.type.Slang",
    "uimaprojects.token.type.Slang:isSlang",
    "uimaprojects.token.type.Slang:probability"})
public class SampleAnnotator extends JCasAnnotator_ImplBase {
  /**
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
    SampleClassifier sampleClassifier;
    
    @Override
    public void initialize(UimaContext aContext) throws ResourceInitializationException {
            // TODO Auto-generated method stub
            super.initialize(aContext);
            try {
                sampleClassifier = SampleClassifier.getInstance();
            } catch (Exception e) {
            }
    }  
    @Override
    public void process(JCas aJCas) {
      String text = aJCas.getDocumentText();
		
        try {
            List<SampleAnnotation> annotations = sampleClassifier.classify(text);
            for(SampleAnnotation annotation:annotations) {
                Slang slang = new Slang(aJCas);
                slang.setBegin(annotation.getTokenBeginIndex()); 
                slang.setEnd(annotation.getTokenEndIndex());
                slang.setIsSlang(annotation.isSlang());
                slang.setProbability(annotation.getProbability());
                slang.addToIndexes();	
                
            }
	} catch (Exception e) {
            getContext().getLogger().log(Level.SEVERE, "Slang annotation exception: " + e);
        }

    }
}
