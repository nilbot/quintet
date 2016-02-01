#!/bin/sh
cd .git/hooks
ln -s ../../git-hooks/gradle_java.pre-commit pre-commit
ln -s ../../git-hooks/recent.post-merge post-merge
cd ../../
echo "Git Hooks setup 完了"
