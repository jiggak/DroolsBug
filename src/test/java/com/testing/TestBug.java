package com.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

public class TestBug {
   KieSession ksession;

   @Before
   public void setup() {
      var kservices = KieServices.Factory.get();
      var kcontainer = kservices.getKieClasspathContainer();
      ksession = kcontainer.newKieSession();
   }

   @After
   public void cleanup() {
      ksession.dispose();
   }

   @Test
   public void test1() {
      var o2 = new Obj2();
      var o3 = new Obj3();
      o3.setObj2(o2);

      ksession.insert(o3);

      ksession.getAgenda().getAgendaGroup("TestRuleGroup").setFocus();

      ksession.fireAllRules();
   }

   @Test
   public void test2() {
      var o1 = new Obj1();
      o1.setS("bar");

      var o2 = new Obj2();
      o2.setObj1(o1);

      var o3 = new Obj3();
      o3.setObj2(o2);

      ksession.insert(o3);

      ksession.getAgenda().getAgendaGroup("TestRuleGroup").setFocus();

      ksession.fireAllRules();
   }
}