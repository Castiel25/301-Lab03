package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.Nullable;

public class AddCityFragment extends DialogFragment {
    interface AddCityDialogListener {
        void addCity(City city);

        void editCity(City oldCity, City newCity);
    }

    private AddCityDialogListener listener;
    // Storing the City being edited
    private City editingCity;

    public static AddCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =
                LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        // Check if we are editing a city or not.
        if (getArguments() != null && getArguments().containsKey("city")) {
            /*  Note to self: getArguments() return a bundle, getSerializable() returns a Serializable Object
            (City) telling Java this is a City */
            editingCity = (City) getArguments().getSerializable("city");
            assert editingCity != null;
            editCityName.setText(editingCity.getName());
            editProvinceName.setText(editingCity.getProvince());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add/Edit a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add/Save", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();

                    if (editingCity != null) {
                        listener.editCity(editingCity, new City(cityName, provinceName));
                    } else {
                        listener.addCity(new City(cityName, provinceName));
                    }
                })
                .create();
    }
}

