package com.example.swingolf.ui.players;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.swingolf.R;
import com.example.swingolf.dataModel.AppDatabase;
import com.example.swingolf.dataModel.DatabaseModule;
import com.example.swingolf.dataModel.Player;
import com.example.swingolf.databinding.FragmentPlayersListBinding;
import com.example.swingolf.ui.StartGame.StartGameViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PlayersListFragment extends Fragment {
    //@Inject
    AppDatabase database;
    private FragmentPlayersListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StartGameViewModel homeViewModel =
                new ViewModelProvider(this).get(StartGameViewModel.class);

        View view = inflater.inflate(R.layout.fragment_players_list, container, false);


        database = DatabaseModule.getInstance(getContext());
        binding = FragmentPlayersListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button addPlayerButton = binding.btnAddPlayer; // view.findViewById(R.id.btnAddPlayer);
        addPlayerButton.setOnClickListener(v -> {
            Log.d("PlayersFragment", " clicked");
            InputDialogFragment dialogFragment = new InputDialogFragment();
            dialogFragment.show(getParentFragmentManager(), "InputDialogFragment");
        });

        getParentFragmentManager().setFragmentResultListener("playerAdded", this, (requestKey, result) -> {
            Log.d("PlayersFragment", "Player added, updating list");
            updatePlayerList();
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("PlayersFragment", "onResume");
        updatePlayerList();
    }

    private void updatePlayerList() {
        List<Player> players = database.playerInDao().getAllPlayers();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        // Remove existing fragments
        for (Fragment fragment : getChildFragmentManager().getFragments()) {
            transaction.remove(fragment);
        }

        // Add new fragments
        for (Player player : players) {
            PlayerItemFragment fragment = PlayerItemFragment.newInstance(player);
            transaction.add(binding.playersContainer.getId(), fragment);  // Assuming playersContainer is an ID of a container in fragment_players.xml
        }

        transaction.commit();
    }
}
