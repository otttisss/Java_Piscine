package edu.school21.spring.preprocessor;

public class PreProcessorToLowerImlp implements PreProcessor {
    @Override
    public String process(String s) {
        return s.toLowerCase();
    }
}
