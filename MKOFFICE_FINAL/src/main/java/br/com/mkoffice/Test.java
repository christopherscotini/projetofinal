package br.com.mkoffice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.mkoffice.utils.MkmtsUtil;

public class Test {

	public static void main(String[] args) {
		
		List<Calendar> aux = new ArrayList<Calendar>();
		List<Calendar> datas = montarDatas();
		try{
			if (datas.size() < 11) {
				for (int i = datas.size()-1; i <= 11 ; i--) {
					if(aux.size() <= 12){
					int diffy = datas.get(i).get(Calendar.YEAR) - datas.get(i-1).get(Calendar.YEAR);
						if(diffy == 0){
							int diffm = datas.get(i).get(Calendar.MONTH) - datas.get(i-1).get(Calendar.MONTH);
							if(diffm == 1){
								aux.add(datas.get(i));
							}else{
								aux.add(datas.get(i));
								for (int j = 1; j < diffm; j++) {
									if(aux.size() <= 12){
										Calendar c1 = Calendar.getInstance();
										c1.set(Calendar.YEAR, datas.get(i).get(Calendar.YEAR));
										c1.set(Calendar.MONTH, datas.get(i).get(Calendar.MONTH)-j);
										c1.set(Calendar.DAY_OF_MONTH, datas.get(i).get(Calendar.DAY_OF_MONTH));
										aux.add(c1);
									}
								}	
							}
						}	
					}
				}
				
				
				
			}
			
		}catch(IndexOutOfBoundsException e){
			aux.add(datas.get(0));
			if(aux.size() <12){
				for (int i = 0; i < 12; i++) {
					if(aux.size() <12){
						Calendar c1 = Calendar.getInstance();
						c1.set(Calendar.YEAR, aux.get(aux.size()-1).get(Calendar.YEAR));
						c1.set(Calendar.MONTH, aux.get(aux.size()-1).get(Calendar.MONTH)-1);
						c1.set(Calendar.DAY_OF_MONTH, aux.get(aux.size()-1).get(Calendar.DAY_OF_MONTH));
						aux.add(c1);
					}
				}
			}
		}
		
		for (int i = 0; i < aux.size(); i++) {
			System.out.println((i+1) +" - "+ MkmtsUtil.converterDataString(aux.get(i).getTime(), "yyyy-MM-dd"));
		}
		
	}

	private static List<Calendar>montarDatas(){
		List<Calendar> c = new ArrayList<Calendar>();
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2014);
		c1.set(Calendar.MONTH, 10);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2014);
		c2.set(Calendar.MONTH, 9);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		Calendar c3 = Calendar.getInstance();
		c3.set(Calendar.YEAR, 2014);
		c3.set(Calendar.MONTH, 7);
		c3.set(Calendar.DAY_OF_MONTH, 1);
		Calendar c4 = Calendar.getInstance();
		c4.set(Calendar.YEAR, 2014);
		c4.set(Calendar.MONTH, 6);
		c4.set(Calendar.DAY_OF_MONTH, 1);
		Calendar c5 = Calendar.getInstance();
		c5.set(Calendar.YEAR, 2014);
		c5.set(Calendar.MONTH, 4);
		c5.set(Calendar.DAY_OF_MONTH, 1);
		Calendar c6 = Calendar.getInstance();
		c6.set(Calendar.YEAR, 2014);
		c6.set(Calendar.MONTH, 1);
		c6.set(Calendar.DAY_OF_MONTH, 1);
		
//		c.add(c6);
//		c.add(c5);
//		c.add(c4);
//		c.add(c3);
//		c.add(c2);
		c.add(c1);
		
		return c;
		
	}
	
}
