package com.example.widestapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.widestapp.R;
import com.example.widestapp.adapter.SliderAdapter;
import com.example.widestapp.fragment.MenuFragment;
import com.example.widestapp.model.AssocProjServFunc;
import com.example.widestapp.model.Employee;
import com.example.widestapp.model.FotosProjeto;
import com.example.widestapp.model.Project;
import com.example.widestapp.model.Service;
import com.example.widestapp.model.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends AppCompatActivity {

    List<Project> projects;

    List<AssocProjServFunc> assoc;

    List<Service> services;

    List<Employee> employees;

    TextView nome, tema, descricao;

    List<FotosProjeto> fotos;

    int codproj, codserv, codfunc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);


        nome = findViewById(R.id.txttituloprojeto);
        tema = findViewById(R.id.txttemaprojeto);
        descricao = findViewById(R.id.txtdescprojeto);

        projects = Project.select(this);


        List<SliderItem> viewPagerItems = new ArrayList<>();

        List<SliderItem> viewpagerFunc = new ArrayList<>();

        fotos = FotosProjeto.select(this);
        employees = Employee.select(this);
        services = Service.select(this);
        assoc = AssocProjServFunc.select(this);

        ViewPager2 viewpager = findViewById(R.id.viewPagerImageSlider);
        ViewPager2 viewpagerfunc = findViewById(R.id.viewPagerImageSliderfunc);

        Intent intent = getIntent();
        String Nome = intent.getStringExtra(MenuFragment.NOME_PROJ);

        for (Project project : projects)
        {
            if (project.getName().equals(Nome))
            {
             codproj = project.getId();
             nome.setText(project.getName());
             tema.setText(project.getTema());
            }
        }

        for (FotosProjeto fotosProjeto : fotos)
        {
            if (fotosProjeto.getIdprojeto().equals(codproj))
            {
                viewPagerItems.add(new SliderItem(Uri.parse(fotosProjeto.getCaminhoFoto()), null, null));
            }
        }

        viewpager.setAdapter(new SliderAdapter(viewPagerItems));

        for (AssocProjServFunc assocs : assoc)
        {
            if (assocs.getIdproj().equals(codproj))
            {
                codserv = assocs.getIdserv();
                codfunc = assocs.getIdfunc();

            }
        }

        for (Service service : services)
        {
            if (service.getId().equals(codserv))
            {
                descricao.setText(service.getName());
            }
        }

        for (Employee employee : employees)
        {
            if (employee.getId().equals(codfunc))
            {
                viewpagerFunc.add(new SliderItem(Uri.parse(employee.getImageName()), employee.getName(), null));
            }
            viewpagerfunc.setAdapter(new SliderAdapter(viewpagerFunc));

        }


    }
}