package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
// import com.google.cloud.datastore.Query;
// import com.google.cloud.datastore.QueryResults;
// import com.google.cloud.datastore.StructuredQuery.OrderBy;
// import com.google.gson.Gson;

// import java.util.ArrayList;
// import java.util.List;


@WebServlet("/form-handler1")
public class wordleFormServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String nameInput = request.getParameter("Guess");
    
    // Print the value so you can see it in the server logs.
    System.out.println(nameInput);
    
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Task");
    FullEntity taskEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("Name", nameInput)
            .build();
    datastore.put(taskEntity);
    
    response.sendRedirect("/index.html");
  }
}

//   @Override
//   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//       Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
//       Query<Entity> query =
//           Query.newEntityQueryBuilder().setKind("Task").setOrderBy(OrderBy.desc("Name")).build();
//       QueryResults<Entity> results = datastore.run(query);
//
//       List<List<String>> tasks = new ArrayList<>();
//       while (results.hasNext()) {
//         Entity entity = results.next();
//        // long id = entity.getKey().getId();
//         String Name = entity.getString("Name");
        
//         List<String> task = new ArrayList<>();
//         task.add(Name);
       
//         tasks.add(task);
//       }
  
//       Gson gson = new Gson();
  
//       response.setContentType("application/json;");
//       response.getWriter().println(gson.toJson(tasks));
//     }
 
