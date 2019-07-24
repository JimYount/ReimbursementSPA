package com.revature.comparing;

import java.util.Comparator;
import java.util.TreeSet;

import com.revature.beans.Animal;
import com.revature.beans.Bean;
import com.revature.beans.Chihuahua;
import com.revature.beans.Dog;

public class ComparingObjects {
	public static void main(String[] args) {
		TreeSet<Bean> beanTree = new TreeSet<Bean>();
		beanTree.add(new Bean("pinto", "tan", 10, .5f, true));
		beanTree.add(new Bean("pinto", "tan", 5, .2f, false));
		beanTree.add(new Bean("pinto", "tan", 4, .4f, true));
		beanTree.add(new Bean("pinto", "white", 4, .4f, false));
		beanTree.add(new Bean("black-eye", "brown", 10, .5f, true));
		beanTree.add(new Bean("pinto", "tan", 4, 3f, false));
		System.out.println(beanTree);
		
		TreeSet<Bean> t = new TreeSet<Bean>(new BeanComparator());
		t.addAll(beanTree);
		System.out.println(t);
		
		Comparator<Bean> bc = (b1, b2) -> {
			if( b1.isTasty() == b2.isTasty())
				return b1.compareTo(b2);
			return (b1.isTasty()) ? 1 : -1;
		};
		
		t = new TreeSet<Bean>(bc);
		t.addAll(beanTree);
		System.out.println(t);
		
		Comparator<Animal> ac = (a1, a2) -> {
			if(a1.isAdorable()!=null && !a1.isAdorable().equals(a2.isAdorable()))
				return a1.isAdorable().compareTo(a2.isAdorable());
			if(a1.getName()!=null)
				return a1.getName().compareTo(a2.getName());
			return 0;
		};
		
		TreeSet<Animal> animalTree = new TreeSet<Animal>(ac);
		Animal a = new Dog();
		a.setName("Rover");
		animalTree.add(a);
		Animal a1 = new Dog();
		a1.setName("Larry");
		System.out.println(animalTree.add(a1));
		System.out.println(a.equals(a1));
		a = new Chihuahua();
		animalTree.add(a);
		System.out.println(animalTree);
	}
}

class BeanComparator implements Comparator<Bean> {

	@Override
	public int compare(Bean b1, Bean b2) {
		if(b1.getYield() == b2.getYield())
			return b1.compareTo(b2);
		return b1.getYield() - b2.getYield();
	}
	
}
