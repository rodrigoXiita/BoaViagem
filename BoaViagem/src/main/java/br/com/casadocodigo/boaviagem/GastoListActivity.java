package br.com.casadocodigo.boaviagem;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GastoListActivity extends ListActivity implements AdapterView.OnItemClickListener {


    private List<Map<String,Object>> gastos;
    private String dataAnterior = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] de = {"data", "descricao", "valor","categoria"};
        int[] para = {R.id.data, R.id.descricao,R.id.valor,R.id.categoria};

        SimpleAdapter adapter = new SimpleAdapter(this, listarGastos(), R.layout.lista_gasto, de, para);
        adapter.setViewBinder(new GastoViewBinder());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Map<String, Object> map = gastos.get(i);
        String descricao = (String) map.get("descricao");
        String mensagem = "Gasto selecionado: " + descricao;
        Toast.makeText(this, mensagem,Toast.LENGTH_SHORT).show();
    }

    private List<Map<String,Object>> listarGastos(){

        gastos = new ArrayList<Map<String, Object>>();
        Map<String,Object> item = new HashMap<String, Object>();
        item.put("data","04/02/2012");
        item.put("descricao", "Diária Hotel");
        item.put("valor", "R$ 260,00");
        item.put("categoria", R.color.categoria_hospedagem);
        gastos.add(item);

        item = new HashMap<String, Object>();
        item.put("data","03/02/2012");
        item.put("descricao", "Wifi");
        item.put("valor", "R$ 7,00");
        item.put("categoria", R.color.categoria_outros);
        gastos.add(item);

        item = new HashMap<String, Object>();
        item.put("data","02/02/2012");
        item.put("descricao", "Táxi Aeroporto - Hotel");
        item.put("valor", "R$ 34,00");
        item.put("categoria", R.color.categoria_transporte);
        gastos.add(item);

        item = new HashMap<String, Object>();
        item.put("data","02/02/2012");
        item.put("descricao", "Sanduíche");
        item.put("valor", "R$ 19,90");
        item.put("categoria", R.color.categoria_alimentacao);
        gastos.add(item);

        return gastos;
    }

    private  class GastoViewBinder implements SimpleAdapter.ViewBinder{


        @Override
        public boolean setViewValue(View view, Object o, String s) {

            if(view.getId() == R.id.data){
                if(!dataAnterior.equals(o)){
                    TextView textView = (TextView) view;
                    textView.setText(s);
                    dataAnterior = s;
                    view.setVisibility(View.VISIBLE);
                }else{
                    view.setVisibility(View.GONE);
                }
                return true;
            }

            if( view.getId()==R.id.categoria ){
                Integer id = (Integer) o;
                view.setBackgroundColor(getResources().getColor(id));
                return true;
            }

            return false;
        }
    };
}
