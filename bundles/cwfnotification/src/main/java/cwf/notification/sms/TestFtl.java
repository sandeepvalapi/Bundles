// package cwf.notification.sms;
//
// import java.io.File;
// import java.io.FileWriter;
// import java.io.StringWriter;
// import java.io.Writer;
// import java.util.HashMap;
// import java.util.Map;
//
// import freemarker.template.Configuration;
// import freemarker.template.Template;
// import freemarker.template.Version;
//
// public class TestFtl {
//
// private static Configuration cfg = null;
//
// static{
// // Freemarker below configuration object deprecated
// //Configuration cfg = new Configuration();
// //Please use this. To make it backward compatible. Please visit here for more
// info:
// //http://freemarker.org/docs/api/freemarker/template/Configuration.html
// cfg = new Configuration();
// }
//
// /*
// * TODO: Use dynamic template, params object
// * create individual mappers for each and every template - NTD
// */
// public static void sendNotification() {
//
// try {
//
// // Load template
// Template template = cfg.getTemplate("resources/Signup.ftl");
//
// // Create data for template
// Map<String, Object> templateData = new HashMap<String, Object>();
// templateData.put("username", "Sandeep");
//
// // Write output on console example 1
// StringWriter out = new StringWriter();
// template.process(templateData, out);
// System.out.println( out.getBuffer().toString() );
// out.flush();
//
// // Call JMS service - Rest
// //DB
//
//// // File output
//// Writer file = new FileWriter(new
// File("resources/BuildXMLTemplateXML.ftl"));
//// template.process(templateData, file);
//// file.flush();
//// file.close();
//
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// }
