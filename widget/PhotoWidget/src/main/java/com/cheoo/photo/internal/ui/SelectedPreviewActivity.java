/*
 * Copyright 2017 Zhihu Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cheoo.photo.internal.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cheoo.photo.internal.entity.Item;
import com.cheoo.photo.internal.entity.SelectionSpec;
import com.cheoo.photo.internal.model.SelectedItemCollection;

import java.util.List;

import cn.ycbjie.ycstatusbarlib.bar.StateAppBar;

public class SelectedPreviewActivity extends BasePreviewActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!SelectionSpec.getInstance().hasInited || getIntent()==null) {
            setResult(RESULT_CANCELED);
            finish();
            return;
        }
        StateAppBar.translucentStatusBar(this, true);
        if (getIntent()!=null){
            Bundle bundle = getIntent().getBundleExtra(EXTRA_DEFAULT_BUNDLE);
            List<Item> selected = bundle.getParcelableArrayList(SelectedItemCollection.STATE_SELECTION);
            if (selected!=null && selected.size()>0){
                mAdapter.addAll(selected);
                mAdapter.notifyDataSetChanged();
                if (mSpec.countable) {
                    mCheckView.setCheckedNum(1);
                } else {
                    mCheckView.setChecked(true);
                }
                mPreviousPos = 0;
                updateSize(selected.get(0));
            }
        }
    }

}