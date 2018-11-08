package cn.mldn.test;

import java.util.HashMap;
import java.util.HashSet;

public class Lark2 {

    class Candi {
        String name;
        HashSet<Comp> appli;
        public Candi(String name) {
            this.name = name;
            this.appli = new HashSet<>();
        }
    }

    class Comp {
        String name;
        HashSet<Candi> candi;
        public Comp (String name) {
            this.name = name;
            this.candi = new HashSet<>();
        }
    }

    private HashMap<String, Candi> CandiMap = new HashMap<>();
    private HashMap<String, Comp> CompMap = new HashMap<>();

    public void add_application(String company_name, String person_name){
        Comp comp = null;
        Candi candi = null;
        if (CompMap.containsKey(company_name)) comp = CompMap.get(company_name);
        else {
            comp = new Comp(company_name);
            CompMap.put(company_name, comp);
        }

        if (CandiMap.containsKey(person_name)) candi = CandiMap.get(person_name);
        else {
            candi = new Candi(person_name);
            CandiMap.put(person_name, candi);
        }

        comp.candi.add(candi);
        candi.appli.add(comp);

    }

    public void delete_application(String company_name, String person_name) {
        if (!CandiMap.containsKey(person_name)) {
            return;
        }
        if (!CompMap.containsKey(company_name)) {
            return;
        }

        Candi person = CandiMap.get(person_name);
        Comp company = CompMap.get(company_name);

        HashSet<Comp> appli = person.appli;
        appli.remove(company);

        HashSet<Candi> candi = company.candi;
        candi.remove(person);

        if (candi.size() == 0) CandiMap.remove(company_name);
        if (appli.size() == 0) CompMap.remove(person_name);
    }

    public String[] get_candidates(String company_name) {

        if (!CompMap.containsKey(company_name)) return null;
        HashSet<Candi> CandiSet = CompMap.get(company_name).candi;
        String[] res = new String[CandiSet.size()];

        int index = 0;
        for (Candi candi: CandiSet) {
            res[index++]= candi.name;
        }
        return res;
    }

    public String[] get_application(String person_name) {

        if (!CandiMap.containsKey(person_name)) return null;
        HashSet<Comp> CompSet = CandiMap.get(person_name).appli;
        String[] res = new String[CompSet.size()];

        int index = 0;
        for (Comp comp: CompSet) {
            res[index ++] = comp.name;
        }

        return res;
    }
}
