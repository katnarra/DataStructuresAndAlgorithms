package oy.interact.tira.student;

import java.util.Comparator;

import oy.interact.tira.model.Coder;

public class CoderNameComparator implements Comparator<Coder>{
    

  @Override
    public int compare(Coder a, Coder b) {
    return a.getCoderName().compareTo(b.getCoderName());
    }
}
