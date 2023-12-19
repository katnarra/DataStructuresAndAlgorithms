package oy.interact.tira.student;

import java.util.Comparator;

import oy.interact.tira.model.Coder;

public class CoderFullNameComparator implements Comparator<Coder>{

    @Override
    public int compare(Coder a, Coder b) { 
        return a.getFullName().compareTo(b.getFullName());
    }
}
