import jess.Deftemplate;
import jess.Fact;
import jess.Funcall;
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;
public class CreateFacts {
	public static int rasp;
	public static Rete engine;
	public static Deftemplate d;
	public static Fact f;
	public static int i;
	public CreateFacts() throws JessException
	{
		engine=new Rete();
		d=new Deftemplate("intrebare","Intrebarea",engine);
		d.addSlot("stringIntrebare", Funcall.NIL, "STRING");
		d.addSlot("raspuns", Funcall.NIL, "STRING");
		engine.addDeftemplate(d);
		//engine.executeCommand("(assert (intrebare (stringIntrebare nil) (raspuns nil)))");
		f=new Fact("intrebare",engine);
		f.setSlotValue("stringIntrebare", new Value(3,RU.INTEGER));
		f.setSlotValue("raspuns", new Value(3,RU.INTEGER));
		engine.assertFact(f);
		i=0;
	}
	public static void createFact() throws JessException {
		engine.store("RASPUNS", new Value(rasp,RU.INTEGER));
		String rule="(defrule este_animal_mare" +
		"(declare (salience 95))"+
		"(intrebare (stringIntrebare 3) (raspuns 3))" +
		" =>" +
		"(bind ?r (fetch RASPUNS))"+
		" (assert (intrebare (stringIntrebare Este_un_animal_mare?) (raspuns ?r)))" +
		"(store INTREBARE Este_un_animal_mare?)" +
		"(store RASPUNS 2))";

		String rule2="(defrule mananca_alune" +
		"(declare (salience 96))"+
		"(intrebare (stringIntrebare Este_un_animal_mare?) (raspuns 0))" +
		/*"(intrebare (stringIntrebare Este_un_animal_mare?) (raspuns ?x))"+
		"(test (not (= ?x 2)))"+*/
		"=>" +
		"(bind ?r (fetch RASPUNS))"+
		" (assert (intrebare (stringIntrebare Mananca_alune?) (raspuns ?r)))" +
		"(store INTREBARE Mananca_alune?)" +
		"(store RASPUNS 2))";

		String rule3="(defrule soarece"+
		"(declare (salience 97))"+
		"(intrebare (stringIntrebare Mananca_alune?) (raspuns 0))" +
		/*"(intrebare (stringIntrebare Mananca_alune?) (raspuns ?x))" +
		"(test (not (= ?x 2)))"+*/
		"=>(store INTREBARE Animalul_este_soarecele)" +
		"(store RASPUNS 2))";

		String rule4="(defrule veverita"+
		"(declare (salience 97))"+
		"(intrebare (stringIntrebare Mananca_alune?) (raspuns 1))" +
		/*"(intrebare (stringIntrebare Mananca_alune?) (raspuns ?x))" +
		"(test (not (= ?x 2)))"+*/
		"=>(store INTREBARE Animalul_este_veverita)" +
		"(store RASPUNS 2))";

		String rule5="(defrule mananca_soareci" +
		"(declare (salience 96))"+
		"(intrebare (stringIntrebare Este_un_animal_mare?) (raspuns 1))" +
		/*"(intrebare (stringIntrebare Este_un_animal_mare?) (raspuns ?x))" +
		"(test (not (= ?x 2)))"+*/
		"=>" +
		"(bind ?r (fetch RASPUNS))"+
		"(assert (intrebare (stringIntrebare Mananca_soareci?) (raspuns ?r)))" +
		"(store INTREBARE Mananca_soareci?)" +
		"(store RASPUNS 2))";

		String rule6="(defrule pisica" +
		"(declare (salience 97))"+
		"(intrebare (stringIntrebare Mananca_soareci?) (raspuns 1))" +
		/*"(intrebare (stringIntrebare Mananca_soareci?) (raspuns ?x))" +
		"(test (not (= ?x 2)))"+*/
		"=>(store INTREBARE Animalul_este_pisica)" +
		"(store RASPUNS 2))";

		String rule7="(defrule stie_sa_inoate" +
		"(declare (salience 97))"+
		"(intrebare (stringIntrebare Mananca_soareci?) (raspuns 0))" +
		/*"(intrebare (stringIntrebare Mananca_soareci?) (raspuns ?x))" +
		"(test (not (= ?x 2)))"+*/
		"=>" +
		"(bind ?r (fetch RASPUNS))"+
		"(assert (intrebare (stringIntrebare Stie_sa_inoate?) (raspuns ?r)))" +
		"(store INTREBARE Stie_sa_inoate?)" +
		"(store RASPUNS 2))";

		String rule8="(defrule ratusca" +
		"(declare (salience 98))"+
		"(intrebare (stringIntrebare Stie_sa_inoate?) (raspuns 1))" +
		/*"(intrebare (stringIntrebare Stie_sa_inoate?) (raspuns ?x))" +
		"(test (not (= ?x 2)))"+*/
		"=>(store INTREBARE Animalul_este_ratusca)" +
		"(store RASPUNS 2))";

		String rule9="(defrule este_ierbivor" +
		"(declare (salience 98))"+
		"(intrebare (stringIntrebare Stie_sa_inoate?) (raspuns 0))" +
		/*"(intrebare (stringIntrebare Stie_sa_inoate?) (raspuns ?x))" +
		"(test (not (= ?x 2)))"+*/
		"=>" +
		"(bind ?r (fetch RASPUNS))"+
		"(assert (intrebare (stringIntrebare Este_ierbivor?) (raspuns ?r)))" +
		"(store INTREBARE Este_ierbivor?)" +
		"(store RASPUNS 2))";

		String rule10="(defrule cal" +
		"(declare (salience 99))"+
		"(intrebare (stringIntrebare Este_ierbivor?) (raspuns 1))" +
		/*"(intrebare (stringIntrebare Este_ierbivor?) (raspuns ?x))" +
		"(test (not (= ?x 2)))"+*/
		"=>(store INTREBARE Animalul_este_calul)" +
		"(store RASPUNS 2))";

		String rule11="(defrule apara_casa" +
		"(declare (salience 99))"+
		"(intrebare (stringIntrebare Este_ierbivor?) (raspuns 0))" +
		/*"(intrebare (stringIntrebare Este_ierbivor?) (raspuns ?x))" +
		"(test (not (= ?x 2)))"+*/
		"=> " +
		"(bind ?r (fetch RASPUNS))"+
		"(assert (intrebare (stringIntrebare Apara_casa?) (raspuns ?r)))" +
		"(store INTREBARE Apara_casa?)" +
		"(store RASPUNS 2))";

		String rule12="(defrule caine" +
		"(declare (salience 100))"+
		"(intrebare (stringIntrebare Apara_casa?) (raspuns 1))" +
		/*"(intrebare (stringIntrebare Apara_casa?) (raspuns ?x))" +
		"(test (not (= ?x 2)))"+*/
		"=>(store INTREBARE Animalul_este_cainele)" +
		"(store RASPUNS 2))";

		String rule13="(defrule unknown" +
		"(declare (salience 100))"+
		"(intrebare (stringIntrebare Apara_casa?) (raspuns 0))" +
		/*"(intrebare (stringIntrebare Apara_casa?) (raspuns ?x))" +
		"(test (not (= ?x 2)))"+*/
		"=>(store INTREBARE Animalul_este_necunoscut)" +
		"(store RASPUNS 2))";

		engine.executeCommand(rule);
		engine.executeCommand(rule2);
		engine.executeCommand(rule3);
		engine.executeCommand(rule4);
		engine.executeCommand(rule5);
		engine.executeCommand(rule6);
		engine.executeCommand(rule7);
		engine.executeCommand(rule8);
		engine.executeCommand(rule9);
		engine.executeCommand(rule10);
		engine.executeCommand(rule11);
		engine.executeCommand(rule12);
		engine.executeCommand(rule13);

		System.out.println("inainte");
		engine.executeCommand("(facts)");
		engine.run();
		engine.retract(f);
		i++;
		System.out.println(i);
		/*f=engine.findFactByID(i);
		engine.retract(f);*/
		for (int j=1; j<2*i-1; j++)
		{
			/*if (j%2==0)
			{*/
			f=engine.findFactByID(j);
			if (f!=null)
				engine.retract(f);
			//}
			
		}
		

		/*ia intrebarea*/
		Value intr=engine.fetch("INTREBARE");
		if (intr!=null)
		{
			String s=intr.stringValue(engine.getGlobalContext());
			s=s.replace('_', ' ');
			rasp=2;

			if (!s.startsWith("Animalul"))
				Interfata.setComponente(s, true);
			else
				Interfata.setComponente(s, false);
		}
		System.out.println("dupa");
		engine.executeCommand("(facts)");
	}
}