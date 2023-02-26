import { AppTheme } from '@lib/services/theme';

type StorageObjectMap = {
  'App/session': {
    id: number;
    username: string;
    token: string;
    role: string;
  };
  'App/theme': AppTheme;
};

export type StorageObjectType = 'App/session' | 'App/theme';

export type StorageObjectData<T extends StorageObjectType> = {
  type: T;
  data: StorageObjectMap[T];
};
