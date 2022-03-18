package com.example.myexamplezuma;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

   private ArrayList<ItemsPojo> listItems;
   private RequestQueue rq;
   private RecyclerView rv1;
   private AdaptadorUsuario adaptadoruser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listItems = new ArrayList<>();
        rq=Volley.newRequestQueue(this);
        for(int i=0;i<20;i++){
            cargarItem();
           rv1=findViewById(R.id.recyclerView);
           LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
           rv1.setLayoutManager(linearLayoutManager);
           adaptadoruser=new AdaptadorUsuario();
           rv1.setAdapter(adaptadoruser);

        }


    }

    private void cargarItem() {

        String url="https://randomuser.me/api/";
        JsonObjectRequest requerimiento=new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                   String valor=response.get("results").toString();
                    JSONArray arreglo =new JSONArray(valor);
                    JSONObject jsonObject = new JSONObject(arreglo.get(0).toString());
                    String name = jsonObject.getJSONObject("name").getString("first");
                    String apellido = jsonObject.getJSONObject("name").getString("last");
                    String foto = jsonObject.getJSONObject("picture").getString("large");
                    String email = jsonObject.getString("email");
                    String genero = jsonObject.getString("gender");
                    ItemsPojo items = new ItemsPojo(name,apellido,foto,genero,email);
                    listItems.add(items);
                    adaptadoruser.notifyItemRangeInserted(listItems.size(),1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("este error muestra", error.getMessage());

                    }
                });
        rq.add(requerimiento);



    }

    private class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.AdaptadorUsuarioHolder> {

        @NonNull
        @Override
        public AdaptadorUsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorUsuarioHolder(getLayoutInflater().inflate(R.layout.item_car,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorUsuarioHolder holder, int position) {

            ItemsPojo contac = listItems.get(position);
            holder.imprimir(position);
            holder.car.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplication(),"hola",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplication(),MainActivity2.class);

                    intent.putExtra("name",contac.getNombre());
                    intent.putExtra("apellido",contac.getApellido());
                    intent.putExtra("email",contac.getEmail());
                    intent.putExtra("genero",contac.getGenero());
                    //intent.putExtra("photo",contac.getFoto());
                    getApplication().startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return listItems.size();
        }

        class AdaptadorUsuarioHolder extends RecyclerView.ViewHolder {
            TextView txtname,txtdire;
            ImageView foto;
            CardView car;
            public AdaptadorUsuarioHolder(@NonNull View itemView) {
                super(itemView);
                txtname=itemView.findViewById(R.id.txt1);
                txtdire=itemView.findViewById(R.id.txt2);
                foto=itemView.findViewById(R.id.photo);
                car =itemView.findViewById(R.id.car);
            }

            public void imprimir(int position) {
                txtname.setText("Nombre: "+listItems.get(position).getNombre());
                txtdire.setText("Correo: "+listItems.get(position).getApellido());
                upphoto(listItems.get(position).getFoto(),foto);

            }


            public void upphoto(String foto,ImageView iv)
            {
                ImageRequest peticion=new ImageRequest(foto,
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                iv.setImageBitmap(response);

                            }
                        },
                        0,
                        0,
                        null,
                        null,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                rq.add(peticion);

            }
        }

    }



}