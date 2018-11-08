package cn.mldn.test;

import java.util.*;

public class Lark {

    private Map<String, HashSet<String>> candi2Com = new HashMap<>();
    private Map<String, HashSet<String>> com2Candi = new HashMap<>();

    public void add_application(String company_name, String person_name){
        if(!com2Candi.containsKey(company_name)){
            com2Candi.put(company_name,new HashSet<String>());
        }
        com2Candi.get(company_name).add(person_name);
        if(!candi2Com.containsKey(person_name)){
            candi2Com.put(person_name,new HashSet<String>());
        }
        candi2Com.get(person_name).add(company_name);
    }

    public void delete_application(String company_name, String person_name) {
        if(com2Candi.containsKey(company_name) && com2Candi.get(company_name).contains(person_name)) {
            com2Candi.get(company_name).remove(person_name);
            if (com2Candi.get(company_name).size() == 0) {
                com2Candi.remove(company_name);
            }
        }

        if(candi2Com.containsKey(person_name) && candi2Com.get(person_name).contains(company_name)){
            candi2Com.get(person_name).remove(company_name);
            if(candi2Com.get(person_name).size()== 0){
                candi2Com.remove(person_name);
            }
        }
    }

    public String[] get_candidates(String company_name) {

        String[] res = new String[com2Candi.get(company_name).size()];

        if (!com2Candi.containsKey(company_name)) return res;

        int index = 0;
        for (String candi: com2Candi.get(company_name)) {
            res[index++] = candi;
        }
        return res;
    }

    public String[] get_application(String person_name) {
        String[] res = new String[candi2Com.get(person_name).size()];

        if (!candi2Com.containsKey(person_name)) return res;

        int index = 0;
        for (String appli: candi2Com.get(person_name)) {
            res[index ++] = appli;
        }
        return res;
    }

    public static void main(String[] args) {
        Lark job_appl = new Lark();
        job_appl.add_application("Lark", "Kelsey");
        job_appl.add_application("Lark", "Emily");
        job_appl.add_application("Lark", "Mike");
        job_appl.add_application("Google", "Mike");
        System.out.println(Arrays.toString(job_appl.get_application("Mike")));
        System.out.println(Arrays.toString(job_appl.get_candidates("Lark")));
        job_appl.delete_application("Lark", "Mike");
        System.out.println(Arrays.toString(job_appl.get_candidates("Lark")));
    }
}
