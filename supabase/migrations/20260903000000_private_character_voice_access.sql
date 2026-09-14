-- Private character voice access foundation for Chiu Know?.
-- Applied first to Supabase project uskxabsodcnzlovuaurp and recorded here for continuity.

create schema if not exists private;
revoke all on schema private from public, anon;
grant usage on schema private to authenticated;

create table if not exists private.app_user_access (
  user_id uuid primary key references auth.users(id) on delete cascade,
  enabled boolean not null default true,
  granted_at timestamptz not null default now()
);

alter table private.app_user_access enable row level security;
revoke all on private.app_user_access from public, anon;
grant select on private.app_user_access to authenticated;

drop policy if exists "users_read_own_access" on private.app_user_access;
create policy "users_read_own_access"
on private.app_user_access
for select
to authenticated
using ((select auth.uid()) = user_id);

insert into storage.buckets (
  id,
  name,
  public,
  file_size_limit,
  allowed_mime_types
)
values (
  'character-voices',
  'character-voices',
  false,
  2097152,
  array['audio/mp4', 'audio/x-m4a', 'audio/m4a']::text[]
)
on conflict (id) do update
set
  public = false,
  file_size_limit = excluded.file_size_limit,
  allowed_mime_types = excluded.allowed_mime_types;

drop policy if exists "authorized_users_read_character_voices" on storage.objects;
create policy "authorized_users_read_character_voices"
on storage.objects
for select
to authenticated
using (
  bucket_id = 'character-voices'
  and exists (
    select 1
    from private.app_user_access access
    where access.user_id = (select auth.uid())
      and access.enabled
  )
);
