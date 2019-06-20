package com.vvitguntur.flowers;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;
import com.vvitguntur.flowers.DataBase.FlowersViewModel;
import com.vvitguntur.flowers.Models.FlowersPojo;
import com.vvitguntur.flowers.Widgets.Widget;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    FlowersPojo pojo;
     @BindView(R.id.largeimage_tv)
    ImageView largeImage;
     @BindView(R.id.tags_tv)
     TextView tags;
     @BindView(R.id.views_tv)
    TextView views;
     @BindView(R.id.comments_tv)
     TextView comments;
     @BindView(R.id.likes_tv)
     TextView likes;
     @BindView(R.id.page_tv)
     Button page;
     @BindView(R.id.database_tv)
     Button database;
     FlowersViewModel mflowersViewModel;
     GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences=getSharedPreferences("projectData",MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        ButterKnife.bind(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, signInOptions);
        pojo = (FlowersPojo) intent.getSerializableExtra("details");
        Picasso.get().load(pojo.getLargeImage()).into(largeImage);
        tags.append(pojo.getTags());
        views.append(pojo.getViews());
        comments.append(pojo.getComments());
        likes.append(pojo.getLikes());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String s=getString(R.string.tandt)+pojo.getTags()+"\n"+getString(R.string.comment)+pojo.getComments()+
                "\n"+getString(R.string.imageurl)+pojo.getLargeImage()+"\n"+getString(R.string.pageurl)+pojo.getPageurl()+"Views:"+
                pojo.getViews();
        editor.putString("data",s);
        editor.apply();
        Intent intent1 = new Intent(this, Widget.class);
        intent1.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(
                new ComponentName(getApplication(), Widget.class));
        intent1.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent1);
        mflowersViewModel=  ViewModelProviders.of(this).get(FlowersViewModel.class);
        check(pojo.getFlowerid());
    }

    public void gotopage(View view) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(pojo.getPageurl()));
    }
    public void check(String id)
    {
        FlowersPojo favMovies=mflowersViewModel.checkMovie(id);
        if(favMovies!=null)
        {
            database.setText("Remove from Myflowers");
        }
        else
        {
            database.setText("Add TO MyFlowers");
        }
    }

    public void addToMyFlowers(View view) {
        String bname = database.getText().toString();
        FlowersPojo p = pojo;
        if (bname.equals("Remove from Myflowers")) {
            mflowersViewModel.delete(p);
            database.setText("Add TO MyFlowers");
        } else {
            mflowersViewModel.insert(p);
            database.setText("Remove from Myflowers");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resource,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.Logout){
            signOut();
        }
        return true;
    }

    private void signOut() {
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            revokeAccess();
                        }
                    });
    }
    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(DetailsActivity.this,MainActivity.class));
                    }
                });
    }
}
