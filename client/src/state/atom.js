import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist();

export const loginState = atom({
  key: 'loginState',
  default: false,
  effects_UNSTABLE: [persistAtom],
});

export const userInfoState = atom({
  key: 'userInfoState',
  default: {},
  effects_UNSTABLE: [persistAtom],
});

export const questionListState = atom({
  key: 'questionListState',
  default: [],
});

export const questionDetailState = atom({
  key: 'questionDetailState',
  default: {},
});

export const answersState = atom({
  key: 'answersState',
  default: [],
});

export const answerListState = atom({
  key: 'answerListState',
  default: [],
});
