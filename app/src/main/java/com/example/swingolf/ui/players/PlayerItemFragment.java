package com.example.swingolf.ui.players;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.swingolf.R;
import com.example.swingolf.dataModel.AppDatabase;
import com.example.swingolf.dataModel.DatabaseModule;
import com.example.swingolf.dataModel.Player;
import com.example.swingolf.databinding.FragmentPlayerItemBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PlayerItemFragment extends Fragment {
    private static final String ARG_PLAYER = "player";
    private Player player;


    AppDatabase database;

    private FragmentPlayerItemBinding binding;

    public static PlayerItemFragment newInstance(Player player) {
        PlayerItemFragment fragment = new PlayerItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLAYER, player);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = DatabaseModule.getInstance(getContext());
        if (getArguments() != null) {
            player = (Player) getArguments().getSerializable(ARG_PLAYER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayerItemBinding.inflate(inflater, container, false);
        binding.playerName.setText(player.name);
        binding.btnPlayerOptions.setOnClickListener(this::showPopupMenu);
        return binding.getRoot();
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(getContext(), view);
        popup.getMenuInflater().inflate(R.menu.player_item_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(this::onMenuItemClick);
        popup.show();
    }

    private boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_edit) {
            editPlayer();
            return true;
        } else if (itemId == R.id.menu_delete) {
            deletePlayer();
            return true;
        }
        return false;
    }

    private void editPlayer() {
        EditPlayerDialogFragment editDialog = EditPlayerDialogFragment.newInstance(player);
        editDialog.show(getParentFragmentManager(), "EditPlayerDialogFragment");
    }

    private void deletePlayer() {
        database.playerInDao().delete(player);
        getParentFragmentManager().setFragmentResult("playerUpdated", new Bundle()); // Changed to "playerUpdated"
    }
}
